import React, { Component } from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";
import Search from "../../components/search";
import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import { Fab } from "@material-ui/core";
import ViewStreamIcon from '@mui/icons-material/ViewStream';
import Cart from "../../components/cart";

class ItemList extends Component {
    constructor(props) {
    super(props);
    this.state = {
        items: [],
        isLoading: false,
        isCreate: false,
        isEdit: false,
        cartItems: [],
        cartHidden: true,
        id: "",
        title: "",
        price: 0,
        description: "",
        category: "",
        quantity: 0,
        quantityCart: 0
    };
    this.handleClickLoading = this.handleClickLoading.bind(this);
    this.handleAddItem = this.handleAddItem.bind(this);
    this.handleCancel = this.handleCancel.bind(this);
    this.handleChangeField = this.handleChangeField.bind(this);
    this.handleSubmitItem = this.handleSubmitItem.bind(this);
    this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
    this.handleToggle = this.handleToggle.bind(this);
    this.handleSearchItem = this.handleSearchItem.bind(this);
    this.handleAddItemToCart = this.handleAddItemToCart.bind(this);
    this.handleCheckout = this.handleCheckout.bind(this);
    }
    componentDidMount() {
        this.loadData();
        this.loadDataCart();
    }
    async loadData() {
        try {
            const { data } = await APIConfig.get("/item");
            this.setState({ items: data.result });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }
    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
        return true;
    }
    handleClickLoading() {
        const currentLoading = this.state.isLoading;
        this.setState({ isLoading: !currentLoading });
        console.log(this.state.isLoading);
    }
    handleAddItem() {
        this.setState({ isCreate:true });
    }
    handleCancel(event) {
        event.preventDefault();
        this.setState({ isCreate:false, isEdit: false });
    }
    handleChangeField(event) {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    }
    async handleSubmitItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.post("/item", data);
                this.setState({
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } 
        catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }
    handleEditItem(item) {
        this.setState({
            isEdit: true,
            id: item.id,
            title: item.title,
            price: item.price,
            description: item.description,
            category: item.category,
            quantity: item.quantity
        })
    }
    async handleSubmitEditItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.put(`/item/${this.state.id}`, data);
                this.setState({
                id: "",
                title: "",
                price: 0,
                description: "",
                category: "",
                quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }
    async handleSearchItem(event) {
        event.preventDefault();
        try {
            const { data } = await APIConfig.get(`/item/?title=${this.state.title}`);
            this.setState({ items: data.result });
            this.setState({ title: "" });
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }
    handleToggle(){
        const cartHidden = this.state.cartHidden;
        this.setState({ cartHidden: !cartHidden });
    }
    async loadDataCart() {
        try {
          const { data } = await APIConfig.get("/cart");
          this.setState({ cartItems: data.result });
        } catch (error) {
          alert("Oops terjadi masalah pada server");
          console.log(error);
        }
    }
    async handleAddItemToCart(event, item) {
        event.preventDefault();
        try {
            const data = {
                idItem: item.id,
                quantity: this.state.quantityCart
            };
            const targetInd = this.state.cartItems.findIndex(
                (it) => it.item.id === item.id
            );
            if ((targetInd) < 0 && (parseInt(this.state.quantityCart) > 0)) {
                if(parseInt(item.quantity) < parseInt(this.state.quantityCart)){
                    alert("Stock isn't sufficient!");
                }
                else{
                    await APIConfig.post("/cart", data);
                    this.setState({
                        quantityCart: 0
                    })
                    this.loadData();
                    this.loadDataCart();
                }
            }
            else{
                if((parseInt(this.state.quantityCart) > 0) && (parseInt(this.state.quantityCart) + parseInt(this.state.cartItems[targetInd].quantity)) <= parseInt(item.quantity)){
                    await APIConfig.post("/cart", data);
                    this.setState({
                        quantityCart: 0
                    })
                    this.loadData();
                    this.loadDataCart();
                }
                else{
                    alert("Stock isn't sufficient!");
                }
            }
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }
    async handleCheckout(event) {
        event.preventDefault();
        try {
            await APIConfig.get("/cart/checkout");
            this.setState({
                cartItems: [],
            });
            this.loadData();
            this.loadDataCart();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }
    render() {
        console.log("render()");
        return (
          <div className = {classes.itemList}>
            <h1 className = {classes.title}>All Items</h1>
            <div style = {{ position: "fixed", top: 25, right: 25}}>
                <Fab variant = "extended" onClick = {this.handleToggle}>
                    {this.state.cartHidden ?
                        <Badge color = "secondary" badgeContent = {this.state.cartItems.length}>
                            <ShoppingCartIcon />
                        </Badge>
                        : <ViewStreamIcon/>}
                </Fab>
            </div>
            {this.state.cartHidden ?
            <div>
                <Search
                    action = {this.handleChangeField}
                    submit = {this.handleSearchItem}
                    namaItem = {this.state.title}
                >
                </Search>
                <Button action = {this.handleAddItem}>Add Item</Button>
                <div>
                    {this.state.items.map((item) => (
                        <Item
                        key = {item.id}
                        id = {item.id}
                        title = {item.title}
                        price = {item.price}
                        description = {item.description}
                        category = {item.category}
                        quantity = {item.quantity}
                        handleEdit = {() => this.handleEditItem(item)}
                        action = {this.handleChangeField}
                        handleAddToCart = {(event) => this.handleAddItemToCart(event, item)}
                        />
                    ))}
                </div>
                <Modal
                    show = {this.state.isCreate || this.state.isEdit}
                    handleCloseModal = {this.handleCancel}
                    modalTitle = {this.state.isCreate
                        ? "Add Item"
                        : `Edit Item ID ${this.state.id}`}
                >
                <form>
                    <input
                        className = {classes.textField}
                        type = "text"
                        placeholder = "Nama Item"
                        name = "title"
                        value = {this.state.title}
                        onChange = {this.handleChangeField}
                    />
                    <input 
                        className = {classes.textField}
                        type = "number"
                        placeholder = "Harga"
                        name = "price"
                        value = {this.state.price}
                        onChange = {this.handleChangeField}
                    />
                    <textarea 
                        className = {classes.textField}
                        placeholder = "Deskripsi"
                        name = "description"
                        rows = "4"
                        value = {this.state.description}
                        onChange = {this.handleChangeField}
                    />
                    <input
                        className = {classes.textField}
                        type = "text"
                        placeholder = "Kategori"
                        name = "category"
                        value = {this.state.category}
                        onChange = {this.handleChangeField}
                    />
                    <input
                        className = {classes.textField}
                        type = "number"
                        placeholder = "Quantity"
                        name = "quantity"
                        value = {this.state.quantity}
                        onChange = {this.handleChangeField}
                    />
                    <Button action = {this.state.isCreate
                        ? this.handleSubmitItem
                        : this.handleSubmitEditItem}
                    >
                        Create
                    </Button>
                    <Button action = {this.handleCancel}>
                        Cancel
                    </Button>
                </form>
            </Modal>
            </div>:
            <div>
                {this.state.cartItems.map((cart) => (
                    <Cart
                        key = {cart.id}
                        id = {cart.id}
                        quantity = {cart.quantity}
                        item = {cart.item}
                    />
                ))}
                {this.state.cartItems.length != 0 ?
                    <Button action = {this.handleCheckout}>Checkout</Button>: ""
                }
            </div>}
        </div>
        );
    }
}

export default ItemList;
import React from "react";
import classes from "./styles.module.css";
import Button from "../../components/button";

const Item = (props) => {
    const { action, id, title, price, description, category, quantity, handleAddToCart, handleEdit, cartQuantity, handleDelete} = props;
    return (
        <div className={classes.item}>
            <h3>{`ID ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Harga: ${price}`}</p>
            <p>{`Deskripsi: ${description}`}</p>
            <p>{`Kategori: ${category}`}</p>
            <p>{`Stok: ${quantity}`}</p>
            <Button action={handleEdit}>
                Edit
            </Button>
            <form>
                <input className = {classes.masukan} type="number" name="cartQuantity" value={cartQuantity} onChange={action} inputStyle="box" placeholder = "Type the item's quantity here" />
                <Button action={handleAddToCart}>
                    Add To Cart
                </Button>
            </form>
        </div>
    );
};

export default Item;
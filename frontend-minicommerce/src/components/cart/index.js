import React from "react";
import classes from "./styles.module.css";
 
const Cart = (props) => {
    const { id, quantity, item } = props;
    const totalHarga = item.price * quantity;
    return (
        <div className = {classes.cart}>
            <h3>{`ID Cart: ${id}`}</h3>
            <h3>{`ID Item: ${item.id}`}</h3>
            <p>{`Nama Barang: ${item.title}`}</p>
            <p>{`Harga: ${item.price}`}</p>
            <p>{`Jumlah: ${quantity}`}</p>
            <p>{`Deskripsi: ${item.description}`}</p>
            <p>{`Kategori: ${item.category}`}</p>
            <p>
                <b>{`Total Harga: ${totalHarga}`}</b>
            </p>
        </div>
    );
};

export default Cart;
import React from "react";
import classes from "./styles.module.css";

const Search = (props) => {
    const { action, submit, namaItem } = props;
    return (
        <div className={classes.search}>
            <h3 className={classes.name}>{`Search Item`}</h3>
            <form>
                <input className = {classes.box} type="text" name="title" value={namaItem} onChange={action} inputStyle="box" placeholder = " Type the item's name here and click enter" />
                <input type="submit" value="Submit" onClick={submit} hidden />
            </form>
        </div>
    );
  };
  
  export default Search;

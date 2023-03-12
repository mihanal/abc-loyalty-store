import { useCallback, useState } from "react";
import { getProducts } from "../service/productApi";

const useProduct = () => {
    const [products, setProducts] = useState([])

    const fetchProducts = useCallback(async () => {
        setProducts(await getProducts());
    }, []);

    return {
        products,
        fetchProducts
    }
}

export default useProduct;
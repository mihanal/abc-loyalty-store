import { useCallback, useState } from "react";
import { getCustomerByContactNumber, saveCustomer } from "../service/customerApi";

const useCustomer = () => {
    const [customer, setCustomer] = useState();
    
    const createCustomer = useCallback(async (customer) => {
        setCustomer(await saveCustomer(customer));
    }, []);

    const fetchCustomer = useCallback(async (contactNumber) => {
        setCustomer(await getCustomerByContactNumber(contactNumber));
    }, []);

    return {
        customer,
        createCustomer,
        fetchCustomer
    }
}

export default useCustomer;
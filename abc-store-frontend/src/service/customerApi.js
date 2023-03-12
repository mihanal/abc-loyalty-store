import axios from 'axios';

const BASE_URL = "http://localhost:8080/api/v1/customer"

export const saveCustomer = async (customer) => {
    const response = await axios.post(BASE_URL, customer);
    return response.data;
};

export const getCustomerByContactNumber = async (contactNumber) => {
    const response = await axios.get(BASE_URL + '/contact', { params: { contactNumber: `${contactNumber}` } });
    return response.data;
};
import axios from 'axios';

const BASE_URL = "http://localhost:8080/api/v1/loyalty"

export const redeemPoints = async (customerId, points) => {
    const response = await axios.put(BASE_URL + `/${customerId}/redeem`, points);
    return response.data;
};

export const addPoints = async (customerId, points) => {
    const response = await axios.put(BASE_URL + `/${customerId}/add`, points);
    return response.data;
};
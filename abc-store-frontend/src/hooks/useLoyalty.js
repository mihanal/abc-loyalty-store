import { useCallback } from "react";
import { redeemPoints, addPoints } from "../service/loyaltyApi";

const useLoyalty = () => {

    const addLoyaltyPoints = useCallback(async (customerId, points) => {
        await addPoints(customerId, points);
    }, []);

    const redeemLoyaltyPoints = useCallback(async (customerId, points) => {
        await redeemPoints(customerId, points);
    }, []);

    return {
        addLoyaltyPoints,
        redeemLoyaltyPoints
    }
}

export default useLoyalty;
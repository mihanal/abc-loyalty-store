import React, { useEffect, useState } from "react";
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import Checkbox from '@material-ui/core/Checkbox';
import Grid from '@material-ui/core/Grid';
import { withStyles, makeStyles } from '@material-ui/core/styles';
import { useHistory } from "react-router-dom";
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableRow from '@material-ui/core/TableRow';
import useProduct from '../hooks/useProduct';
import useCustomer from "../hooks/useCustomer";
import useLoyalty from "../hooks/useLoyalty";

const useStyles = makeStyles((theme) => ({
    paper: {
        width: '50%',
        marginLeft: 'auto',
        marginRight: 'auto'
    },
    root: {
        width: '100%',
        maxWidth: 380,
        backgroundColor: theme.palette.background.paper,
        position: 'relative',
        overflow: 'auto',
        maxHeight: 300,
        marginLeft: '115px'
    },
    table: {
        marginLeft: '60px',
        width: '75%'
    }
}));
const StyledTableCell = withStyles((theme) => ({
    head: {
        backgroundColor: theme.palette.common.black,
        color: theme.palette.common.white,
    },
    body: {
        fontSize: 14,
    },
}))(TableCell);

const StyledTableRow = withStyles((theme) => ({
    root: {
        '&:nth-of-type(odd)': {
            backgroundColor: theme.palette.action.hover,
        },
    },
}))(TableRow);

const Loyalty = () => {
    const classes = useStyles();
    const [checked, setChecked] = React.useState([]);
    const [contactNumber, setContactNumber] = useState('');
    const [orderTotal, setOrderTotal] = useState(0);
    const [pointsAccumulating, setPointsAccumulating] = useState(0);
    const [pointsRedeemable, setPointsRedeemable] = useState(0);
    const [redeem, setRedeem] = useState(false);
    const [points, setPoints] = useState(0);

    const { products, fetchProducts } = useProduct();
    const { customer, fetchCustomer } = useCustomer();
    const { addLoyaltyPoints, redeemLoyaltyPoints } = useLoyalty();

    const history = useHistory();

    useEffect(() => {
        setPointsRedeemable(customer?.loyaltyReward?.redeemablePoints)
    }, [customer, redeem]);

    const handleToggle = (e, product) => {
        const newChecked = [...checked]
        if (e.target.checked) {
            newChecked.push(product);
        }
        else {
            const index = newChecked.map(function (e) { return e.id; }).indexOf(product.id);
            newChecked.splice(index, 1)
        }
        setChecked(newChecked);
        setOrderTotal(newChecked.reduce((n, { price }) => n + price, 0))
        setPointsAccumulating(newChecked.reduce((n, { loyaltyPoints }) => n + loyaltyPoints, 0))
    }

    const loadProductData = () => {
        fetchCustomer(contactNumber);
        fetchProducts();
    }

    const loadRedeem = () => {
        setRedeem(redeem => !redeem);
    }

    const cancelRedeem = () => {
        setRedeem(false);
    }

    const redeemPoints = () => {
        const updatedPoints = customer.loyaltyReward.availablePoints - points
        const updatedRedeemablePts = customer.loyaltyReward.redeemablePoints - points
        const reward = { ...customer.loyaltyReward, availablePoints: updatedPoints, redeemablePoints: updatedRedeemablePts }
        redeemLoyaltyPoints(customer.customerId, reward);
        setRedeem(false);
        fetchCustomer(contactNumber);
        setOrderTotal(orderTotal - points)
        setPointsRedeemable(pointsRedeemable - points)
    }

    const placeOrder = () => {
        const updatedPoints = !!customer.loyaltyReward ? customer.loyaltyReward.availablePoints + pointsAccumulating : pointsAccumulating
        const updatedRedeemablePoints = !!customer.loyaltyReward ? customer.loyaltyReward.redeemablePoints + pointsAccumulating : pointsAccumulating
        const reward = { ...customer.loyaltyReward, availablePoints: updatedPoints, redeemablePoints: updatedRedeemablePoints }
        addLoyaltyPoints(customer.customerId, reward);

        history.push("/")
    }

    return (
        <>
            <div className={classes.paper}>
                <Grid container spacing={0}>
                    <Grid item xs={4}>
                        <div style={{ marginTop: '8px' }}>Customer Mobile Number :</div>
                    </Grid>
                    <Grid item xs={4}>
                        <TextField
                            name="contactNumber"
                            variant="outlined"
                            id="contactNumber"
                            size="small"
                            value={contactNumber}
                            onChange={(e) => {
                                setContactNumber(e.target.value);
                            }}
                        /></Grid>
                    <Grid item xs={4}>
                        <Button
                            type="submit"
                            variant="contained"
                            color="primary"
                            style={{ maxHeight: '60px', marginLeft: '10px' }}
                            onClick={loadProductData}
                        >
                            Make Order
                        </Button>
                    </Grid>
                </Grid>
                <br></br><br></br>
                {
                    !!customer && (
                        <>
                            <List dense className={classes.root}>
                                {
                                    products.map((product) => {
                                        return (
                                            <ListItem key={product.id} button>
                                                <ListItemText id={product.id} primary={product.name} secondary={product.description} style={{ width: '100px' }} />
                                                <ListItemText id={product.id} primary="Price" secondary={product.price} />
                                                <Checkbox
                                                    edge="end"
                                                    onChange={(e) => handleToggle(e, product)}
                                                    checked={checked.map(function (e) { return e.id; }).indexOf(product.id) !== -1}
                                                    inputProps={{ 'aria-labelledby': product.id }}
                                                />
                                            </ListItem>
                                        )
                                    })
                                }
                            </List>
                            <br></br><br></br>
                            <Table className={classes.table} aria-label="customized table">
                                <TableBody>
                                    <StyledTableRow>
                                        <StyledTableCell component="th" scope="row"> Order Total </StyledTableCell>
                                        <StyledTableCell align="right">{orderTotal}</StyledTableCell>
                                    </StyledTableRow>
                                    <StyledTableRow>
                                        <StyledTableCell component="th" scope="row"> Total Loyalty Points Accumulating </StyledTableCell>
                                        <StyledTableCell align="right">{pointsAccumulating}</StyledTableCell>
                                    </StyledTableRow>
                                    <StyledTableRow>
                                        <StyledTableCell component="th" scope="row"> Redeemable Points </StyledTableCell>
                                        <StyledTableCell align="right">{pointsRedeemable}</StyledTableCell>
                                        <StyledTableCell>
                                            {
                                                !redeem && (
                                                    <Button
                                                        type="button"
                                                        variant="contained"
                                                        color="primary"
                                                        style={{ width: '10px' }}
                                                        onClick={loadRedeem}
                                                    >
                                                        Redeem
                                                    </Button>
                                                )
                                            }
                                            {
                                                redeem && (
                                                    <>
                                                        <TextField
                                                            name="points"
                                                            variant="outlined"
                                                            id="firstName"
                                                            size="small"
                                                            style={{ width: '150px' }}
                                                            value={points}
                                                            onChange={(e) => {
                                                                setPoints(e.target.value);
                                                            }}
                                                        />
                                                        <div style={{ marginTop: '10px' }}>
                                                            <Button
                                                                type="button"
                                                                variant="contained"
                                                                color="primary"
                                                                style={{ width: '10px' }}
                                                                onClick={redeemPoints}
                                                            >
                                                                OK
                                                            </Button>
                                                            <Button
                                                                type="button"
                                                                variant="contained"
                                                                color="primary"
                                                                style={{ width: '10px', marginLeft: '20px' }}
                                                                onClick={cancelRedeem}
                                                            >
                                                                Cancel
                                                            </Button>
                                                        </div>
                                                    </>
                                                )
                                            }
                                        </StyledTableCell>
                                    </StyledTableRow>
                                </TableBody>
                            </Table>
                            <br></br><br></br>
                            <Button
                                type="submit"
                                fullWidth
                                variant="contained"
                                color="primary"
                                style={{ width: '550px', marginLeft: '65px' }}
                                onClick={placeOrder}
                            >
                                Place Order
                            </Button>
                        </>
                    )

                }
            </div>
        </>
    )
}

export default Loyalty;
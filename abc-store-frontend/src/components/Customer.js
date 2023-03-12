import React from "react";
import Avatar from '@material-ui/core/Avatar';
import Button from '@material-ui/core/Button';
import CssBaseline from '@material-ui/core/CssBaseline';
import TextField from '@material-ui/core/TextField';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import { makeStyles } from '@material-ui/core/styles';
import Container from '@material-ui/core/Container';
import { FormLabel, Radio, RadioGroup } from "@material-ui/core";
import useCustomer from "../hooks/useCustomer";
import { useHistory } from "react-router-dom";
import { useForm } from "react-hook-form";

const useStyles = makeStyles((theme) => ({
    paper: {
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center',
    },
    avatar: {
        backgroundColor: theme.palette.secondary.main,
    },
    form: {
        width: '100%'
    }
}));

const Customer = () => {
    const classes = useStyles();
    const { register, handleSubmit } = useForm();
    const { createCustomer } = useCustomer();

    const history = useHistory();

    const onSubmit = (data) => {
        createCustomer(data);
        history.push("/loyalty")
    };

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline />
            <div className={classes.paper}>
                <Avatar className={classes.avatar}>
                </Avatar>
                <Typography component="h1" variant="h5">
                    Create Customer
                </Typography>
                <form className={classes.form} onSubmit={handleSubmit(onSubmit)}>
                    <Grid container spacing={2}>
                        <Grid item xs={12}>
                            <TextField
                                name="firstName"
                                variant="outlined"
                                required
                                fullWidth
                                id="firstName"
                                label="First Name"
                                autoFocus
                                {...register("firstName")}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                name="lastName"
                                variant="outlined"
                                required
                                fullWidth
                                id="lastName"
                                label="Last Name"
                                {...register("lastName")}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                name="birthDate"
                                variant="outlined"
                                required
                                fullWidth
                                id="birthDate"
                                label="Birth Date"
                                type="date"
                                InputLabelProps={{
                                    shrink: true,
                                  }}
                                {...register("birthDate")}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <TextField
                                variant="outlined"
                                required
                                fullWidth
                                name="contactNumber"
                                label="Contact Number"
                                id="contactNumber"
                                {...register("contactNumber")}
                            />
                        </Grid>
                        <Grid item xs={12}>
                            <FormLabel id="demo-row-radio-buttons-group-label">Gender</FormLabel>
                            <RadioGroup
                                row
                                aria-labelledby="demo-row-radio-buttons-group-label"
                                name="row-radio-buttons-group"
                            >
                                <FormControlLabel value="FEMALE" name="gender" control={<Radio />} label="Female" {...register("gender")} />
                                <FormControlLabel value="MALE" name="gender" control={<Radio />} label="Male" {...register("gender")} />
                            </RadioGroup>
                        </Grid>
                    </Grid>
                    <Button
                        type="submit"
                        fullWidth
                        variant="contained"
                        color="primary"
                    >
                        Create Customer
                    </Button>
                </form>
            </div>
        </Container>
    )
}

export default Customer;
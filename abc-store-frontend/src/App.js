import { Route, Switch } from "react-router-dom";
import Home from "./components/Home";
import { NavLink } from "react-router-dom";
import Customer from "./components/Customer";
import { makeStyles } from "@material-ui/core";
import Loyalty from "./components/Loyalty";

const useStyles = makeStyles((theme) => ({
  nav: {
    padding: '12px'
  }
}));

const App = () => {
  const classes = useStyles();
  
  return (
    <div className={classes.nav}>
      <nav>
        <ul>
          <li>
            <NavLink exact to="/" activeClassName="purple" activeStyle={{ fontWeight: "bold" }} > Home </NavLink>
          </li>
          <li>
            <NavLink to="/add-user" activeClassName="purple" activeStyle={{ fontWeight: "bold" }} > Customer </NavLink>
          </li>
          <li>
            <NavLink to="/loyalty" activeClassName="purple" activeStyle={{ fontWeight: "bold" }} > Loyalty </NavLink>
          </li>
        </ul>
      </nav>
      <Switch>
        <Route exact path="/"><Home /></Route>
        <Route path="/add-user"><Customer /></Route>
        <Route path="/loyalty"><Loyalty /></Route>
      </Switch>
    </div>
  );
}

export default App;
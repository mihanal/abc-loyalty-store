import { makeStyles } from '@material-ui/core/styles';
const useStyles = makeStyles((theme) => ({
  main: {
    width: '55%',
    margin: 'auto',
    padding: '1px',
    textAlign: 'center'
  }
}));

const Home = () => {
  const classes = useStyles();
  return (
    <div className={classes.main}>
      <h2>ABC STORE</h2>
      <img src="/images/HomePage.jpg" alt="ABC STORE" width="860" height="545"></img>
    </div>
  );
}

export default Home;

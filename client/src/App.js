import logo from './img.png';
import './App.css';
import QueryInput from "./components/QueryInput";


function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1 class="Page-title">Boolean model</h1>
      </header>
        <div className="QueryInput">
            <QueryInput/>
        </div>
    </div>
  );
}


export default App;

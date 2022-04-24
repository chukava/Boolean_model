import logo from './img.png';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1 class="Page-title">Boolean model</h1>
      </header>
        <div>
            <form >
                <label>Query:
                    <input type="text" />
                </label>
            </form>
        </div>
    </div>
  );
}

function MyForm() {
  return (
    <form>
      <label>Enter your name:
        <input type="text" />
      </label>
    </form>
  )
}

export default App;

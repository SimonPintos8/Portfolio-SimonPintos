import './App.css';
import ListEmployee from './components/ListEmployee';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import FooterComponent from './components/FooterComponent';
import HeaderComponent from './components/HeaderComponent';
import CreateEmployee from './components/CreateEmployee'
import ViewEmployee from './components/ViewEmployee';

function App() {
  return (
    <div>
      <Router>
        
          <HeaderComponent/>
          <div className="container">
            <Routes>
              <Route path='/' element = {<ListEmployee/>}></Route>
              <Route path='/employees' element = {<ListEmployee/>}></Route>
              <Route path='/add-employee/:id' element = {<CreateEmployee/>}></Route>
              <Route path='/view-employee/:id' element = {<ViewEmployee/>}></Route>
            </Routes>
          </div>
          <FooterComponent/>
        
      </Router>
    </div>
  );
}

export default App;

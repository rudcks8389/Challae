import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import PageTemplate from './components/PageTemplate';
import TeamApproval from './components/TeamApproval';
import MemberManagement from './components/MemberManagement';
import DashBoard from './components/DashBoard';
import Clublist from './components/ClubList';

function App() {
  return (
    <Router>
      <Routes>
        <Route path='/' element={<PageTemplate />} />
        <Route path='/dashboard' element={<DashBoard />} />
        <Route path='/member' element={<MemberManagement />} />
        <Route path='/club' element={<Clublist />} />
        <Route path='/team' element={<TeamApproval />} />
      </Routes>
    </Router>
  );
}

export default App;

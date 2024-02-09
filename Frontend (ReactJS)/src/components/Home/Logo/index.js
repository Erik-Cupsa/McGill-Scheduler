import Writing from '../../../assets/Writing.png';
import './index.scss';

const Logo = () => {

  return (
    <div className="logo-container">
      <img className="solid-logo animate" src={Writing} alt="Student Studying" />
    </div>
  );
};

export default Logo;
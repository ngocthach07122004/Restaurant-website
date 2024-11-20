import UserManagement from "./../AdminPages/UserManagement/index"

const Home = () => {
  return (
    <div>
      <h1>Welcome to the Home Page!</h1>
      <p>This is the main landing page of your application.</p>
      <UserManagement />
    </div>
  );
};

export default Home;

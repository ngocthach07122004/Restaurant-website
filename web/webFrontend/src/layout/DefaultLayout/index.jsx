import React from 'react';
import Header from '../Header';
import Footer from '../Footer';

const DefaultLayout = ({ children }) => {
  return (
    <div className="d-flex flex-column min-vh-100">
      <Header />
      <main className="flex-grow-1" style={{ marginTop: '76px' }}>
        {children}
      </main>
      <Footer />
    </div>
  );
};

export default DefaultLayout;
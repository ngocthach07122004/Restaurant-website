import React from 'react';
import AdminHeader from './AdminHeader'
import AdminFooter from './AdminFooter'; // Optional: Footer specific to admin
import { Outlet } from 'react-router-dom';

const AdminLayout = () => {
  return (
    <div className="d-flex flex-column min-vh-100">
      <AdminHeader />
      <main className="flex-grow-1" style={{ marginTop: '76px' }}>
        <Outlet /> {/* Render admin pages here */}
      </main>
      <AdminFooter />
    </div>
  );
};

export default AdminLayout;

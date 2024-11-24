// import React from 'react';
import { Space, Table, Tag } from 'antd';
import { useState, useEffect } from "react";
import { toast } from "react-toastify";
import { Button, Input, Form } from "antd";
import { useNavigate } from 'react-router-dom';
import "./styles.scss"

// import AdminDashboard from './index';


const AdminDashboard = () => {
    return (
      <div className="dashboard">
        <div className="box box1">Box1</div>
        <div className="box box2">Box2</div>
        <div className="box box3">Box3</div>
        <div className="box box4">Box4</div>
        <div className="box box5">Box5</div>
        <div className="box box6">Box6</div>
        <div className="box box7">Box7</div>
        <div className="box box8">Box8</div>
        <div className="box box9">Box9</div>
      </div>
    );
};
export default AdminDashboard;
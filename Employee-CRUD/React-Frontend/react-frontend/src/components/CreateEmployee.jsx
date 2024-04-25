import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import EmployeeService from '../services/EmployeeService';

const CreateEmployee = () => {
    const { id } = useParams();
    const navigate = useNavigate(); 
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [emailId, setEmailId] = useState('');

    useEffect(() => {
        if (id !== '_add') {
        EmployeeService.getEmployeeById(id).then((response) => {
            const employee = response.data;
            setFirstName(employee.firstName);
            setLastName(employee.lastName);
            setEmailId(employee.emailId);
        }).catch((error) => {
            console.log('Error fetching employee:', error);
        });
        }
    }, [id]);

    const saveOrUpdateEmployee = async (e) => {
        e.preventDefault();
        const employee = { firstName, lastName, emailId };

        try {
        if (id === '_add') {
            await EmployeeService.createEmployee(employee);
        } else {
            await EmployeeService.updateEmployee(employee, id);
        }
        navigate('/employees');
        } catch (error) {
        console.log('Error:', error);
        }
    };

    const getTitle = () => {
        return id === '_add' ? <h3 className='text-center'>Add Employee</h3> : <h3 className='text-center'>Update Employee</h3>;
    };

    return (
    <div className='container'>
        <div className='row'>
            <div className='card col-md-6 offset-md-3'>
                {getTitle()}
                <div className='card-body'>
                    <form>
                        <div className='form-group'>
                            <label>First Name:</label>
                            <input
                            name='firstName'
                            placeholder='First Name'
                            className='form-control'
                            value={firstName}
                            onChange={(e) => setFirstName(e.target.value)}
                            />
                        </div>
                        <div className='form-group'>
                            <label>Last Name:</label>
                            <input
                            name='lastName'
                            placeholder='Last Name'
                            className='form-control'
                            value={lastName}
                            onChange={(e) => setLastName(e.target.value)}
                            />
                        </div>
                        <div className='form-group'>
                            <label>Email Address:</label>
                            <input
                            name='emailId'
                            placeholder='Email Address'
                            className='form-control'
                            value={emailId}
                            onChange={(e) => setEmailId(e.target.value)}
                            />
                        </div>
                        <button className='btn btn-success' onClick={saveOrUpdateEmployee}>
                            Save
                        </button>
                        <Link to='/employees' className='btn btn-danger' style={{ marginLeft: '10px' }}>
                            Cancel
                        </Link>
                    </form>
                </div>
            </div>
        </div>
    </div>
    );
};

export default CreateEmployee;
import React, { useState } from 'react';
import axios from 'axios';

function AddAcademyTo(props) {
  const [academy, setAcademy] = useState({
    name: '',
    location: '',
    description: '',
    email: '',
    phone: ''
  });

  const handleSubmit = (e) => {
    e.preventDefault();
    axios.post(`/academyOwner/addacademy/${props.email}`, academy)
      .then(res => {
        console.log(res.data);
        setAcademy({
          name: '',
          location: '',
          description: '',
          email: '',
          phone: ''
        });
      })
      .catch(err => console.log(err));
  }

  const handleChange = (e) => {
    const { name, value } = e.target;
    setAcademy(prevAcademy => ({
      ...prevAcademy,
      [name]: value
    }));
  }

  return (
    <form onSubmit={handleSubmit}>
      <label>Name:</label>
      <input type="text" name="name" value={academy.name} onChange={handleChange} required />

      <label>Location:</label>
      <input type="text" name="location" value={academy.location} onChange={handleChange} required />

      <label>Description:</label>
      <textarea name="description" value={academy.description} onChange={handleChange} required />

      <label>Email:</label>
      <input type="email" name="email" value={academy.email} onChange={handleChange} required />

      <label>Phone:</label>
      <input type="tel" name="phone" value={academy.phone} onChange={handleChange} required />

      <button type="submit">Add Academy</button>
    </form>
  );
}

export default AddAcademyTo;

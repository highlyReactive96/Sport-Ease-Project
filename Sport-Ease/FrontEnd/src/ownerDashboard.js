function OwnerDashboard() {
    
    const academyId = sessionStorage.getItem('academyId')

    return ( 
        <h1>{academyId}</h1>
     );
}

export default OwnerDashboard;
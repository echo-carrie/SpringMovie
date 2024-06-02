document.getElementById('registerForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const username = document.getElementById('username').value;
    const password = document.getElementById('password').value;
    const email = document.getElementById('email').value;
    const vip = document.getElementById('vip').checked;

    const response = await fetch('/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            username: username,
            password: password,
            email: email,
            vip: vip
        })
    });

    if (response.ok) {
        alert('User registered successfully');
    } else {
        alert('Failed to register user');
    }
});

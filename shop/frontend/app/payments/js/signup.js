document.getElementById('signup-form').onsubmit = async function(e) {
    e.preventDefault();
    let username = this.username.value.trim();
    let resp = await fetch(
        'http://localhost:8080/payments/signup',
        {
            method: 'POST',
            headers: {'Content-Type': 'application/json; charset=utf-8'},
            body: JSON.stringify({username})
        });
    let text = await resp.text();
    document.getElementById('result').textContent = text;
};


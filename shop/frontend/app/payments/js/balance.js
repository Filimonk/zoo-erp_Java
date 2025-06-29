document.getElementById('balance-form').onsubmit = async function(e) {
    e.preventDefault();
    let username = this.username.value.trim();
    let url = 'http://localhost:8080/payments/balance?username=' + encodeURIComponent(username);
    let resp = await fetch(url);
    let text = await resp.text();
    document.getElementById('result').textContent = text;
};


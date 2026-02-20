const cardsDiv = document.getElementById("cards-contas");
const statusDiv = document.getElementById("status");

// Função para atualizar cards de contas
function listarContas() {
    fetch("http://localhost:8080/api/contas")
        .then(res => res.json())
        .then(contas => {
            cardsDiv.innerHTML = "";
            contas.forEach(conta => {
                const saldoClass = conta.saldo > 0 ? "text-success" : "text-danger";
                const card = document.createElement("div");
                card.className = "col-md-4 mb-3";
                card.innerHTML = `
                    <div class="card h-100 shadow-sm">
                        <div class="card-body">
                            <h5 class="card-title">${conta.pessoa.nome}</h5>
                            <p class="card-text"><strong>Conta:</strong> ${conta.numeroConta}</p>
                            <p class="card-text"><strong>Email:</strong> ${conta.pessoa.email}</p>
                            <p class="card-text"><strong>CPF:</strong> ${conta.pessoa.cpf}</p>
                            <p class="card-text ${saldoClass}"><strong>Saldo:</strong> ${conta.saldo}</p>
                        </div>
                    </div>
                `;
                cardsDiv.appendChild(card);
            });
        });
}

// Função para mostrar mensagens
ffunction mostrarMensagem(mensagem, tipo="success") {
    statusDiv.innerHTML = `<div class="alert alert-${tipo}" role="alert">${mensagem}</div>`;
}
}

// Criar conta
document.getElementById("form-criar-conta").addEventListener("submit", e => {
    e.preventDefault();
    const pessoa = {
        nome: document.getElementById("nome").value,
        email: document.getElementById("email").value,
        cpf: document.getElementById("cpf").value
    };

    fetch("http://localhost:8080/api/contas", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(pessoa)
    })
    .then(res => res.json())
    .then(data => {
        mostrarMensagem(`Conta criada com sucesso! Número da Conta: <strong>${data.numeroConta}</strong>`);
        listarContas();
        e.target.reset();
    });
});

// Depositar
document.getElementById("form-depositar").addEventListener("submit", e => {
    e.preventDefault();
    const numeroConta = document.getElementById("conta-depositar").value;
    const valor = document.getElementById("valor-depositar").value;

    fetch(`http://localhost:8080/api/contas/${numeroConta}/depositar?valor=${valor}`, { method: "POST" })
        .then(res => res.json())
        .then(data => {
            mostrarMensagem(`Depósito realizado! Conta ${data.numeroConta} - Saldo atualizado: ${data.saldo}`, "success");
            listarContas();
            e.target.reset();
        });
});

// Sacar
document.getElementById("form-sacar").addEventListener("submit", e => {
    e.preventDefault();
    const numeroConta = document.getElementById("conta-sacar").value;
    const valor = document.getElementById("valor-sacar").value;

    fetch(`http://localhost:8080/api/contas/${numeroConta}/sacar?valor=${valor}`, { method: "POST" })
        .then(res => res.json())
        .then(data => {
            mostrarMensagem(`Saque realizado! Conta ${data.numeroConta} - Saldo atualizado: ${data.saldo}`, "warning");
            listarContas();
            e.target.reset();
        });
});

// Transferir
document.getElementById("form-transferir").addEventListener("submit", e => {
    e.preventDefault();
    const origem = document.getElementById("conta-origem").value;
    const destino = document.getElementById("conta-destino").value;
    const valor = document.getElementById("valor-transferir").value;

    fetch(`http://localhost:8080/api/contas/${origem}/transferir/${destino}?valor=${valor}`, { method: "POST" })
        .then(res => res.text())
        .then(msg => {
            mostrarMensagem(msg, "info");
            listarContas();
            e.target.reset();
        });
});

// Inicializa
listarContas();

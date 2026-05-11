# Medicamentos API

API REST para gerenciamento de medicamentos, com autenticação JWT e controle de acesso por perfil de usuário.

---

## Base URL

```
https://medicamentos-api-n18s.onrender.com/api
```

---

## Autenticação

A API utiliza **JWT (JSON Web Token)**. Para acessar endpoints protegidos, você precisa primeiro fazer login e incluir o token retornado no cabeçalho das requisições.

```
Authorization: Bearer <seu_token_aqui>
```

### Perfis de Acesso

| Perfil  | Permissões |
|---------|------------|
| `USER`  | Leitura de medicamentos |
| `ADMIN` | Leitura + criação, edição e exclusão de medicamentos |

---

## Endpoints de Autenticação

### Registrar novo usuário

```http
POST /auth/register
Content-Type: application/json

{
  "username": "novousuario",
  "senha": "senha123"
}
```

### Login

```http
POST /auth/login
Content-Type: application/json

{
  "username": "admin",
  "senha": "senha"
}
```

**Resposta de sucesso:** retorna um token JWT que deve ser usado nas requisições protegidas.

> **Usuários padrão disponíveis:**
> - Admin: `username: admin` / `senha: senha`
> - Usuário comum: `username: user` / `senha: senha`

---

## Endpoints de Medicamentos

### Listar todos os medicamentos

Endpoint público — não requer autenticação.

```http
GET /medicamentos
```

### Buscar medicamento por ID

```http
GET /medicamentos/{id}
```

**Exemplo:**
```http
GET /medicamentos/dipirona-1g-2ml
```

### Criar medicamento

> Requer perfil **ADMIN**.

```http
POST /medicamentos
Content-Type: application/json
Authorization: Bearer <token>

{
  "id": "test-antibiotico-500mg",
  "name": "Test Antibiótico",
  "variation": "500 mg / 1 mL",
  "volumeMl": 1,
  "amountMg": 500,
  "mgPerKgDefault": 20,
  "description": "Descrição do medicamento",
  "indications": ["Indicação 1", "Indicação 2"],
  "image": "/images/ampola.svg"
}
```

### Atualizar medicamento

> Requer perfil **ADMIN**.

```http
PUT /medicamentos/{id}
Content-Type: application/json
Authorization: Bearer <token>

{
  "id": "test-antibiotico-500mg",
  "name": "Test Antibiótico ATUALIZADO",
  "variation": "500 mg / 2 mL",
  "volumeMl": 2,
  "amountMg": 500,
  "mgPerKgDefault": 25,
  "description": "Descrição atualizada",
  "indications": ["Indicação 1", "Indicação 2", "Indicação 3"],
  "image": "/images/ampola.svg"
}
```

### Excluir medicamento

> Requer perfil **ADMIN**.

```http
DELETE /medicamentos/{id}
Authorization: Bearer <token>
```

---

## Campos do Medicamento

| Campo            | Tipo       | Descrição                              |
|------------------|------------|----------------------------------------|
| `id`             | `string`   | Identificador único (ex: `dipirona-1g-2ml`) |
| `name`           | `string`   | Nome do medicamento                    |
| `variation`      | `string`   | Concentração e volume (ex: `1 g / 2 mL`) |
| `volumeMl`       | `number`   | Volume em mililitros                   |
| `amountMg`       | `number`   | Quantidade em miligramas               |
| `mgPerKgDefault` | `number`   | Dose padrão em mg/kg                   |
| `description`    | `string`   | Descrição do medicamento               |
| `indications`    | `string[]` | Lista de indicações                    |
| `image`          | `string`   | Caminho para a imagem (opcional)       |

---

## Códigos de Resposta

| Código | Significado |
|--------|-------------|
| `200`  | Sucesso |
| `201`  | Criado com sucesso |
| `401`  | Não autenticado — token ausente ou inválido |
| `403`  | Não autorizado — perfil sem permissão |
| `404`  | Medicamento não encontrado |

---

## Exemplo de Fluxo Completo

**1. Fazer login como admin e guardar o token:**
```http
POST https://medicamentos-api-n18s.onrender.com/api/auth/login
Content-Type: application/json

{ "username": "admin", "senha": "senha" }
```

**2. Criar um medicamento com o token recebido:**
```http
POST https://medicamentos-api-n18s.onrender.com/api/medicamentos
Content-Type: application/json
Authorization: Bearer <token>

{ "id": "acido-ascorbico-500mg", "name": "Ácido Ascórbico", ... }
```

**3. Verificar se foi criado:**
```http
GET https://medicamentos-api-n18s.onrender.com/api/medicamentos/acido-ascorbico-500mg
```

**4. Atualizar o medicamento:**
```http
PUT https://medicamentos-api-n18s.onrender.com/api/medicamentos/acido-ascorbico-500mg
Content-Type: application/json
Authorization: Bearer <token>

{ ...campos atualizados... }
```

**5. Excluir o medicamento:**
```http
DELETE https://medicamentos-api-n18s.onrender.com/api/medicamentos/acido-ascorbico-500mg
Authorization: Bearer <token>
```

**6. Confirmar exclusão (deve retornar 404):**
```http
GET https://medicamentos-api-n18s.onrender.com/api/medicamentos/acido-ascorbico-500mg
```

# Crédito API

API REST para consulta de créditos constituídos, desenvolvida com Spring Boot, PostgreSQL e Docker. Esta aplicação permite registrar, listar e buscar créditos por número de crédito ou número de NFS-e.

---

## 🚀 Tecnologias

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker / Docker Compose
- JUnit + Mockito
- Lombok

---

## 🛠️ Configuração do Ambiente

### Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)
- Java 17 (caso queira rodar localmente sem Docker)

---

## ▶️ Como subir a aplicação

```bash
docker-compose up --build


🔍 Buscar crédito por número

GET /api/creditos/credito/{numeroCredito}

Exemplo:

![Detalhe.png](docs%2FDetalhe.png)

GET http://localhost:8081/api/creditos/credito/123456


📑 Buscar todos os créditos por número de NFS-e

GET /api/creditos/{numeroNfse}


![tela1.png](docs%2Ftela1.png)




🗃️ Carga de Dados
A aplicação insere dados automaticamente se a tabela credito estiver vazia, via data.sql.

📷 Exemplo dos dados iniciais:



🐳 Arquitetura com Docker
A aplicação é composta por:

📦 credito-api: Backend Spring Boot

🐘 db_credito: Banco de dados PostgreSQL

📌 Diagrama simples:

📁 Estrutura do Projeto

src/
├── main/
│   ├── java/com/infuse/credito
│   └── resources/
│       ├── application.yml
│       ├── data.sql
│       └── schema.sql
└── test/

📝 Autor
Desenvolvido por Leandro Meireles — GitHub


✅ Acessando o Kafka UI
Abra seu navegador e vá para:

http://localhost:8082

![kafka.png](docs%2Fkafka.png)

![kafka2.png](docs%2Fkafka2.png)









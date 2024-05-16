
## Visão Geral
Esta é uma API para o gerenciamento de tarefas (To-Do List) desenvolvida usando Java com Spring Boot e SQL Server. A API fornece endpoints para criar, atualizar, listar e deletar tarefas.

## Configuração

### Pré-requisitos
- Java 17 ou superior
- Maven
- SQL Server Express ou outra instância do SQL Server
- IDE de sua preferência (IntelliJ, Eclipse, etc.)

### Configuração do Banco de Dados
-- Crie o banco de dados, se não existir
IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'GerenciadorDB')
BEGIN
    CREATE DATABASE GerenciadorDB;
END
GO

-- Use o banco de dados recém-criado ou o existente
USE GerenciadorDB;
GO

-- Crie a tabela "Tasks", se não existir
IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'Tasks')
BEGIN
    CREATE TABLE Tasks (
        id INT IDENTITY(1,1) PRIMARY KEY,
        title NVARCHAR(255) NOT NULL,
        description NVARCHAR(MAX) NULL,
        creation_date DATETIME NOT NULL DEFAULT GETDATE(),
        status NVARCHAR(50) NOT NULL
    );
END
GO

### Configuração do Projeto
 --Clone o repositório:

git clone https://github.com/andrey02/gerenciador-tarefas.git
cd gerenciador-tarefas


Navegue para a branch develop:

git checkout develop


### Build e execute o projeto:

mvn clean install
mvn spring-boot:run

### Uso

Endpoints
GET /tasks: Lista todas as tarefas.

POST /tasks: Cria uma nova tarefa.

Exemplo de requisição:
{
    "title": "Minha tarefa",
    "description": "Descrição da tarefa",
    "creationDate": "2024-05-16T14:49:50",
    "status": "pendente"
}
Exemplo de resposta:
{
    "id": 1,
    "title": "Minha tarefa",
    "description": "Descrição da tarefa",
    "creationDate": "2024-05-16T14:49:50",
    "status": "pendente"
}
PUT /tasks/{id}: Atualiza uma tarefa existente.

Exemplo de requisição:
{
    "title": "Minha tarefa atualizada",
    "description": "Descrição atualizada",
    "creationDate": "2024-05-16T14:49:50",
    "status": "concluída"
}
DELETE /tasks/{id}: Deleta uma tarefa existente.

Exemplo de resposta:
{
    "message": "Task deletada com sucesso"
}
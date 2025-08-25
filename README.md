# 🚀 ITAIPU - Sistema de Microsserviços

Sistema de microsserviços desenvolvido em **Java 17** com **Quarkus**, implementando arquitetura distribuída com comunicação entre serviços via **HTTP REST**. O projeto demonstra boas práticas de desenvolvimento de microsserviços, containerização com Docker e integração com APIs externas.

## 🏗️ Arquitetura do Sistema

<img width="711" height="558" alt="image" src="https://github.com/user-attachments/assets/b1c38482-b692-4901-acd0-40fe6f6718e5" />


### **Características da Arquitetura**
- **Microsserviços independentes** com responsabilidades bem definidas
- **Comunicação síncrona** via HTTP REST
- **Banco de dados separado** para cada contexto
- **Containerização completa** para facilitar deploy
- **Health checks** para monitoramento
- **Documentação automática** com OpenAPI/Swagger

## 🎯 Funcionalidades Implementadas

### **💰 API de Moedas (Porta 8080)**
- **Listar moedas disponíveis** - `GET /api/currency/available`
- **Obter cotação específica** - `GET /api/currency/quote/{from}/{to}`
- **Obter múltiplas cotações** - `GET /api/currency/quotes/{base}?currencies={moedas}`
- **Integração com AwesomeAPI** para cotações em tempo real

### **🛒 Sistema de Carrinho (Porta 8081)**
- **Gestão de Produtos:**
  - CRUD completo de produtos
  - Categorização e busca
  - Controle de estoque
- **Gestão de Carrinhos:**
  - Criação e gestão de carrinhos
  - Adição/remoção de itens
  - Cálculo de totais com conversão de moedas
  - Integração com API de moedas para conversões

## 🚀 Tecnologias Utilizadas

- **Backend:** Java 17, Quarkus 3.24.3
- **Build Tool:** Gradle
- **Banco de Dados:** PostgreSQL 15
- **Containerização:** Docker, Docker Compose
- **Comunicação:** HTTP REST, REST Client
- **Documentação:** OpenAPI/Swagger
- **Monitoramento:** Health Checks, Logs estruturados

## 📁 Estrutura do Projeto

```
microsservices/
├── projeto-1-api-moedas/          # API de Cotações de Moedas
│   ├── src/main/java/br/com/itaipu/
│   │   ├── api/                   # Controllers REST
│   │   ├── client/                # Cliente para API externa
│   │   ├── model/                 # Modelos de dados
│   │   └── service/               # Lógica de negócio
│   ├── src/main/resources/
│   │   └── application.properties # Configurações
│   └── src/main/docker/
│       └── Dockerfile.jvm         # Container JVM
├── projeto-2-system-cart/         # Sistema de Carrinho de Compras
│   ├── src/main/java/br/com/itaipu/
│   │   ├── api/                   # Controllers REST
│   │   ├── entity/                # Entidades JPA
│   │   ├── repository/            # Repositórios Panache
│   │   ├── service/               # Lógica de negócio
│   │   └── client/                # Cliente para API de moedas
│   ├── src/main/resources/
│   │   └── application.properties # Configurações
│   └── src/main/docker/
│       └── Dockerfile.jvm         # Container JVM
├── docker-compose.yml             # Orquestração dos containers
└── README.md                      # Esta documentação
```

## ⚠️ Pré-requisitos

- **Java 17** ou superior
- **Docker** e **Docker Compose**
- **Git** para controle de versão

## 🚀 Como Executar

### **Execução Rápida com Docker**

```bash
# 1. Clone o repositório
git clone https://github.com/matteusmanoel/microsservices.git
cd microsservices

# 2. Execute com Docker Compose
docker-compose up --build

# 3. Acesse as APIs:
# - API Moedas: http://localhost:8080
# - System Cart: http://localhost:8081
# - PostgreSQL: localhost:5432
```

### **Desenvolvimento Local**

```bash
# API Moedas
cd projeto-1-api-moedas
./gradlew quarkusDev

# System Cart (em outro terminal)
cd projeto-2-system-cart
./gradlew quarkusDev
```

## 🧪 Testes e Validação

### **Endpoints de Monitoramento**

- **Health Checks:**
  - API Moedas: `http://localhost:8080/q/health`
  - System Cart: `http://localhost:8081/health`
- **Documentação:**
  - API Moedas: `http://localhost:8080/q/swagger-ui`
  - System Cart: `http://localhost:8081/swagger-ui`

### **Testes via Postman**

Collection completa disponível para importação com todos os endpoints documentados e organizados por funcionalidade.

## 🔮 Roadmap Futuro

### **Fase 2: Mensageria e Eventos**
- [ ] Integração com Apache Kafka
- [ ] Comunicação assíncrona entre serviços
- [ ] Event sourcing para auditoria

### **Fase 3: Segurança e Autenticação**
- [ ] Integração com Keycloak
- [ ] Autenticação JWT
- [ ] Autorização baseada em roles

### **Fase 4: Monitoramento e Observabilidade**
- [ ] Métricas com Prometheus
- [ ] Logs centralizados com ELK Stack
- [ ] Tracing distribuído com Jaeger

### **Fase 5: Escalabilidade e Performance**
- [ ] Cache distribuído com Redis
- [ ] Load balancing
- [ ] Auto-scaling com Kubernetes

## 📊 Status Atual

- ✅ **API Moedas:** Funcionando perfeitamente
- ✅ **System Cart:** Funcionando perfeitamente
- ✅ **Comunicação:** Funcionando entre os serviços
- ✅ **Banco de Dados:** Funcionando com dados iniciais
- ✅ **Docker:** Funcionando perfeitamente
- 🔄 **Kafka:** Em desenvolvimento
- 🔄 **Keycloak:** Em desenvolvimento

## 🤝 Como Contribuir

### **1. Fork o projeto**
```bash
git clone https://github.com/seu-usuario/microsservices.git
cd microsservices
```

### **2. Crie uma branch para sua feature**
```bash
git checkout -b feature/AmazingFeature
```

### **3. Commit suas mudanças**
```bash
git commit -m 'Add some AmazingFeature'
```

### **4. Push para a branch**
```bash
git push origin feature/AmazingFeature
```

### **5. Abra um Pull Request**

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Matteus Manoel**
- **GitHub:** [@matteusmanoel](https://github.com/matteusmanoel)
- **LinkedIn:** [Perfil LinkedIn](https://linkedin.com/in/seu-perfil)

## 🙏 Agradecimentos

- **Quarkus Team** pelo framework excepcional
- **AwesomeAPI** pela API de cotações gratuita
- **Comunidade Java** pelo suporte e conhecimento compartilhado

## 📞 Suporte

- **Issues:** [GitHub Issues](https://github.com/matteusmanoel/microsservices/issues)
- **Discussions:** [GitHub Discussions](https://github.com/matteusmanoel/microsservices/discussions)

---

**⭐ Se este projeto foi útil para você, considere dar uma estrela no repositório!**

---

## 🏷️ Tags/Topics

```
quarkus, java, microservices, docker, postgresql, rest-api, 
gradle, quarkus-framework, java-17, microservices-architecture, 
docker-compose, postgres, rest-client, openapi, swagger, 
health-checks, panache, hibernate
```

## 📋 Comandos Úteis

### **Desenvolvimento**
```bash
# Build dos projetos
./gradlew build                    # Build individual
docker-compose up --build         # Build e execução completa

# Logs dos containers
docker-compose logs -f            # Todos os serviços
docker-compose logs -f system-cart # Apenas system-cart

# Parar serviços
docker-compose down               # Parar e remover containers
docker-compose down -v            # Parar e remover volumes
```

### **Manutenção**
```bash
# Limpar build
./gradlew clean                   # Limpar build local

# Verificar status
docker-compose ps                # Status dos containers
docker-compose logs              # Logs dos containers
```

---

**Desenvolvido com ❤️ usando Quarkus e Java 17**

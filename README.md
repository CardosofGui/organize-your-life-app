# Organize Your Life
<p>O aplicativo se chama Organize Your Life e é um aplicativo to do list com o propósito do usuário armazenar suas tarefas e organizar o seu dia a dia para melhorar a gestão de seu tempo.
<p>Tive o auxilio do Desenvolvedor Android Ezequiel Messore para desenvolver esse aplicativo com o objetivo de recriar uma versão de minha autoria.
    
### Funcionalidades
* Sistema de cadastro e login de usuarios;
* Criação de tarefas, onde é solicitado: Titulo, Descrição, Data e Hora;
* É possivel excluir, editar e marcar tarefas como finalizada;

### Como armazenamos os dados?
<p>No projeto apresentado no Bootcamp o professor realizou o armazenamento dos dados na mémoria do app, porém esse metódo fazia com que os dados fossem excluidos após o encerramento da aplicação.
<p>Visto esse problema, decidi juntar os conhecimentos WEB que estou adquirindo no meu curso técnico e desenvolvi uma API Rest bem simples, porém capaz de armazenar os dados em um banco de dados MySQL, onde a hospedei no servidor da heroku.com

<p>Em uma implementação futura pretendo adicionar a biblioteca Room no aplicativo para que dados também sejam armazenados em cache e que não dependam apenas de uma conexão da internet do usuario.

## Telas criadas
### <p align="center">SplashScreen
<table align="center">
    <td align="center">
        <p align="center">Tentando se conectar com a API</p>
        <img src="https://i.ibb.co/ykLT8XF/Whats-App-Image-2021-06-26-at-17-36-40-10.jpg" alt="Your image title" width="300px" />
    </td>
    <td align="center">
        <p align="center">Sem conexão com a Internet ou API fora de AR</p>
        <img src="https://i.ibb.co/WvvnZDv/Whats-App-Image-2021-06-26-at-17-36-40-9.jpg" alt="Your image title" width="300px" />
    </td>
</table>

### <p align="center">Usuario
<table align="center">
    <td align="center">
        <p align="center">Login</p>
        <img src="https://i.ibb.co/YjRFy2S/Whats-App-Image-2021-06-26-at-17-36-40-7.jpg" alt="Your image title" width="300px" />
    </td>
    <td align="center">
        <p align="center">Cadastro</p>
        <img src="https://i.ibb.co/JskfMrg/Whats-App-Image-2021-06-26-at-17-36-40-8.jpg" alt="Your image title" width="300px" />
    </td>
</table>

### <p align="center">Tarefas
<table align="center">
    <td align="center">
        <p align="center">Nenhuma tarefa encontrada</p>
        <img src="https://i.ibb.co/4ZxzqK7/Whats-App-Image-2021-06-26-at-17-36-40-6.jpg" alt="Your image title" width="300px" />
    </td>
    <td align="center">
        <p align="center">Exibindo tarefa</p>
        <img src="https://i.ibb.co/hf4G0nc/Whats-App-Image-2021-06-26-at-17-36-40-5.jpg" alt="Your image title" width="300px" />
    </td>
    <td align="center">
        <p align="center">Finalizar tarefa</p>
        <img src="https://i.ibb.co/XSwNQqR/Whats-App-Image-2021-06-26-at-17-36-40-4.jpg" alt="Your image title" width="300px" />
    </td>
    <td align="center">
        <p align="center">Excluir tarefa</p>
        <img src="https://i.ibb.co/93Zdbm9/Whats-App-Image-2021-06-26-at-17-36-40-3.jpg" alt="Your image title" width="300px" />
    </td>
</table>

### <p align="center">Formulario tarefa
<table align="center">
    <td align="center">
        <p align="center">Criar tarefa</p>
        <img src="https://i.ibb.co/vvHQxSB/Whats-App-Image-2021-06-26-at-17-36-40-2.jpg" alt="Your image title" width="300px" />
    </td>
    <td align="center">
        <p align="center">Editar tarefa</p>
        <img src="https://i.ibb.co/NpR5pGM/Whats-App-Image-2021-06-26-at-17-36-40-1.jpg" alt="Your image title" width="300px" />
    </td>
</table>



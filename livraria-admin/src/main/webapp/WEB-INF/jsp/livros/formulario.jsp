<ul class="errors">
    <c:forEach items="${errors}" var="error">
        <li>
                ${error.category}: ${error.message}
        </li>
    </c:forEach>
</ul>
<form action="${linkTo[LivrosController].salva}" method="post" enctype="multipart/form-data">
    <input type="hidden" name="livro.id" value="${livro.id}"/>
    <h2>Formulário de cadastro de livros</h2>
    <ul>
        <li>Titulo: <br/>
            <input type="text" name="livro.titulo" value="${livro.titulo}"/></li>
        <li>Descricao: <br/>
            <textarea name="livro.descricao">${livro.descricao}</textarea></li>
        <li>ISBN: <br/>
            <input type="text" name="livro.isbn" value="${livro.isbn}"/></li>
        <li>Preco: <br/>
            <input type="text" name="livro.preco" value="${livro.preco}"/></li>
        <li>Data de publicacao: <br/>
            <input type="text" name="livro.dataPublicacao" value="${livro.dataPublicacao}"/></li>
        <li>Capa: <input type="file" name="capa" /></li>
    </ul>
    <input type="submit" value="Salvar"/>
</form>

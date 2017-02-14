<h3>Veja as últimas ofertas para você:</h3>
<ul class="livros">
    <c:forEach items="${livros}" var="livro">
        <li class="livro">${livro.titulo} - R$ ${livro.preco}</li>
    </c:forEach>
</ul>

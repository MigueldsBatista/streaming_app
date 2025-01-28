const API_videoS = '/api/video-educacional-aberto';
const urlParams = new URLSearchParams(window.location.search);
const cursoId = urlParams.get('id');
window.onload = async () => {
carregarvideosDoCurso(cursoId);
}
// Lista todos os videos
async function carregarvideos() {
    const response = await fetch(API_videoS);
    const videos = await response.json();
    return videos;
}

function carregarvideosDoCurso(cursoId) {
    const courseContainer = document.querySelector("#aula-container");
    const courseDescription = courseContainer.querySelector("#aula-description");
    courseDescription.innerHTML = ""; // Limpa a descrição do curso antes de preencher
    obtervideosPorCurso(cursoId).then(videos => {
        videos.forEach(video => {
            const div = document.createElement("div");
            
            div.className = "video";
            div.textContent = video.titulo;
            courseDescription.appendChild(div);
        });
    }).catch(error => {
        console.error('Erro ao carregar os videos do curso:', error);
    });
}

// Função para carregar os videos em um select
export function carregarObjetovideo() {
    const selectvideos = document.querySelector("#videos");
    selectvideos.innerHTML = ""; // Limpa a lista de videos antes de preencher
    carregarvideos().then(videos => {
        videos.forEach(video => {
            const option = document.createElement("option");
            option.value = video.id;
            option.textContent = video.titulo;
            selectvideos.appendChild(option);
        });
    }).catch(error => {
        console.error('Erro ao carregar os videos:', error);
    });
}

// Nova função para carregar videos por autor
async function carregarvideosPorAutor(autorId) {
    const response = await fetch(`${API_videoS}/autor/${autorId}`);
    const videos = await response.json();
    return videos;
}

// Função para carregar os videos por autor em um select
function carregarvideosPorAutorSelect(autorId) {
    const selectvideos = document.querySelector("#videos");
    selectvideos.innerHTML = ""; // Limpa a lista de videos antes de preencher
    carregarvideosPorAutor(autorId).then(videos => {
        videos.forEach(video => {
            const option = document.createElement("option");
            option.value = video.id;
            option.textContent = video.titulo;
            selectvideos.appendChild(option);
        });
    }).catch(error => {
        console.error('Erro ao carregar os videos por autor:', error);
    });
}

// Nova função para carregar videos por categoria
async function carregarvideosPorCategoria(categoria) {
    const response = await fetch(`${API_videoS}/categoria/${categoria}`);
    const videos = await response.json();
    return videos;
}

// Função para carregar os videos por categoria em um select
function carregarvideosPorCategoriaSelect(categoria) {
    const selectvideos = document.querySelector("#videos");
    selectvideos.innerHTML = ""; // Limpa a lista de videos antes de preencher
    carregarvideosPorCategoria(categoria).then(videos => {
        videos.forEach(video => {
            const option = document.createElement("option");
            option.value = video.id;
            option.textContent = video.titulo;
            selectvideos.appendChild(option);
        });
    }).catch(error => {
        console.error('Erro ao carregar os videos por categoria:', error);
    });
}

// Nova função para buscar um video por ID
async function carregarvideoPorId(id) {
    const response = await fetch(`${API_videoS}/${id}`);
    const video = await response.json();
    return video;
}

// Função para exibir os detalhes de um video (por exemplo, em uma página de detalhes)
function exibirDetalhesvideo(id) {
    carregarvideoPorId(id).then(video => {
        const detalhes = document.querySelector("#detalhesvideo");
        detalhes.innerHTML = `
            <h2>${video.titulo}</h2>
            <p>${video.descricao}</p>
            <p>Categoria: ${video.categoria}</p>
            <p>Autor: ${video.autor}</p>
        `;
    }).catch(error => {
        console.error('Erro ao carregar o video por ID:', error);
    });
}

// Função para salvar um novo video
async function salvarvideo(video) {
    const response = await fetch(API_videoS, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(video)
    });
    const novovideo = await response.json();
    return novovideo;
}

// Função para salvar um novo video (exemplo de uso em um formulário)
function salvarvideoFormulario() {
    const video = {
        titulo: document.querySelector("#titulo").value,
        descricao: document.querySelector("#descricao").value,
        categoria: document.querySelector("#categoria").value,
        autorId: document.querySelector("#autorId").value
    };

    salvarvideo(video).then(novovideo => {
        console.log('Novo video salvo:', novovideo);
    }).catch(error => {
        console.error('Erro ao salvar o video:', error);
    });
}

// Nova função para atualizar um video
async function atualizarvideo(id, video) {
    const response = await fetch(`${API_videoS}/${id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(video)
    });
    const videoAtualizado = await response.json();
    return videoAtualizado;
}

// Função para atualizar um video (exemplo de uso em um formulário)
function atualizarvideoFormulario(id) {
    const video = {
        titulo: document.querySelector("#titulo").value,
        descricao: document.querySelector("#descricao").value,
        categoria: document.querySelector("#categoria").value,
        autorId: document.querySelector("#autorId").value
    };

    atualizarvideo(id, video).then(videoAtualizado => {
        console.log('video atualizado:', videoAtualizado);
    }).catch(error => {
        console.error('Erro ao atualizar o video:', error);
    });
}

// Função para deletar um video
async function deletarvideo(id) {
    const response = await fetch(`${API_videoS}/${id}`, {
        method: 'DELETE'
    });
    return response.ok;
}

// Função para deletar um video (exemplo de uso)
function deletarvideoFormulario(id) {
    deletarvideo(id).then(success => {
        if (success) {
            console.log('video deletado com sucesso');
        } else {
            console.error('Erro ao deletar o video');
        }
    }).catch(error => {
        console.error('Erro ao deletar o video:', error);
    });
}

//TODO
async function obtervideosPorCurso(cursoId) {
    const response = await fetch(`${API_videoS}/${cursoId}/videos`);
    const videos = await response.json();
    return videos;
}

// Função para carregar os videos por curso em um select
function carregarvideosPorCurso(cursoId) {
    const selectvideos = document.querySelector("#aula-container");
    selectvideos.innerHTML = ""; // Limpa a lista de videos antes de preencher
    obtervideosPorCurso(cursoId).then(videos => {
        videos.forEach(video => {
            const div = document.createElement("div");
            div.innerHTML = `
                <h3>Titulo: ${video.titulo}</h3>
                <p>Descricao: ${video.descricao}</p>
                <p>Categoria: ${video.categoria}</p>
                <p>Autor: ${video.autor}</p>
            `;
            selectvideos.appendChild(div);
        });
    }).catch(error => {
        console.error('Erro ao carregar os videos por curso:', error);
    });
}

// Nova função para listar todas as categorias
async function listarCategorias() {
    const response = await fetch(`${API_videoS}/listar-categorias`);
    const categorias = await response.json();
    return categorias;
}

// Função para carregar categorias em um select
function carregarCategoriasSelect() {
    const selectCategorias = document.querySelector("#categorias");
    selectCategorias.innerHTML = ""; // Limpa a lista de categorias antes de preencher
    listarCategorias().then(categorias => {
        categorias.forEach(categoria => {
            const option = document.createElement("option");
            option.value = categoria;
            option.textContent = categoria;
            selectCategorias.appendChild(option);
        });
    }).catch(error => {
        console.error('Erro ao carregar as categorias:', error);
    });
}

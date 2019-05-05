$(document).ready(function () {
    $('h1:first').after('<hr/>');
    if (nameCurrentFile() !== "p_0_p.html") {
        $('body').append('<hr/><a href="p_0_p.html">Главная</a>');
        $('body').append('<div id="nameCurrentFile">Номер страницы: ' + numberCurrentFile() + '</div>');
    }
    delBraces();
});

/**
 * Возвращает имя файла, например "index.html"
 * @returns {string|.parseUrl.pathname|*}
 */
function nameCurrentFile() {
    //путь, пример - может быть так "/F:/Folder1/file.html" или даже так "/D:\Working\index.html"
    var pa = document.location.pathname;

    var re = /^(.*)[\\\/](.*?)$/;
    var ex = re.exec(pa);
    if (ex !== null) {
        pa = ex[2];
    }
    return pa;
}

function numberCurrentFile() {
    var nme = nameCurrentFile();
    var m = /\d+/.exec(nme);
    return m[0];
}

/**
 * Удаление квадратных скобок в заголовке
 */
function delBraces(){
    var t = $('h1:first').text();
    t = t.replace("[[", '').replace("]]", '');
    $('h1:first').text(t);
}

<?php
ini_set('display_errors', 1); 
error_reporting(E_ALL);
require_once('mysql_connection.php');
$html_rows = '';
$query = "SELECT movieID, movieTitle, movieGenre, movieRating, URL FROM santilabs.my_movies";
if ($stmt = $conn->prepare($query)) {
    $stmt->execute();
    $stmt->bind_result($movieID, $movieTitle, $movieGenre, $movieRating, $URL);
    while ($stmt->fetch()) {
        $html_rows .= format_row($movieID, $URL, $movieTitle, $movieGenre, $movieRating);
    }
}
$conn->close();
$table = displayMovies($html_rows);
echo $table;
exit;
///////////////////////////////////////
function format_row($movieID, $URL, $movieTitle, $movieGenre, $movieRating) {
    $movieURL = "<a href=\"$URL\" title=\"Movie Trailer\" target=\"_blank\">$movieTitle</a>";
    $html_row=<<<rows
                        <tr>
                            <td data-search="$movieTitle">$movieURL</td>
                            <td data-search="$movieGenre">$movieGenre</td>
                            <td>$movieRating</td>
                            <td><span onclick="getPoster(this, '$movieTitle')"><b>Show Poster</b></span></td>
                            <td>Delete</td>
                        </tr>
rows;
return $html_row;
}
//////////////////////////////////////
function displayMovies($movie_rows) {
    $html_table =<<<mytable
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=us-ascii">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>DataTables example - Movies</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.9/css/jquery.dataTables.min.css">
    <script type="text/javascript" language="javascript" src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" language="javascript" src="https://cdn.datatables.net/1.10.9/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript" class="init">
        $(document).ready(function() {
            $('#example').DataTable();
        } );

        function getPoster(id, movieTitle) {
            myAJAX = new XMLHttpRequest();
            myAJAX.open('GET', "askOMDB.php?movieTitle="+movieTitle, true);
            myAJAX.send();
            myAJAX.onreadystatechange = function() {
                if(myAJAX.readyState == 4 && myAJAX.status==200) {
                    id.innerHTML = myAJAX.responseText;
                }
            }
        }
    </script>
</head>
<body class="wide comments example">
    <a name="top"></a>
    <div class="fw-container">
        <div class="fw-body">
            <div class="content">
                <h1 class="page_title">Movies</h1>
                <table id="example" class="display" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th>Title</th>
                            <th>Genre</th>
                            <th>Rating</th>
                            <th>Poster</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tfoot>
                        <tr>
                            <th>Title</th>
                            <th>Genre</th>
                            <th>Rating</th>
                            <th>Poster</th>
                            <th>Action</th>
                        </tr>
                    </tfoot>
                    <tbody>
                        $movie_rows
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
mytable;
    return $html_table;
}
?>
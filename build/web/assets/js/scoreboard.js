/* 
* Displays the score of the players current game, their all time best and the top leader.
*/
function ScoreBoard() {
   $.getJSON('ws/wsleaders', function (data) {
       $tbl = $('#tblScore');      
       $.each(data.leaders, function (i, f) {
           var tblRow = "<tr>" + "<td>" + f.firstName + "</td>" + "<td>" + f.lastName + "</td>" + "<td>" + f.scoreValue + "</td>" + "</tr>";
           $(tblRow).appendTo("#tblScore tbody");
       });
   });
};
function PlayerScore() {
   $.getJSON('ws/wsleaders', function (data) {
       $tbl = $('#tblScore');      
       $.each(data.leaders, function (i, f) {
           var tblRow = "<tr>" + "<td>" + f.firstName + "</td>" + "<td>" + f.lastName + "</td>" + "<td>" + f.scoreValue + "</td>" + "</tr>";
           $(tblRow).appendTo("#tblScore tbody");
       });
   });
}
var container = document.getElementById('score2');
var scoreboard = new ScoreBoard();
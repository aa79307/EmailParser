var http = require("http");
var port = 3000;
var serverUrl = "localhost";
var mysql      = require('mysql');
var value;
var length;

 

var connection = mysql.createConnection({
  host     : 'localhost',
  user     : 'root',
  password : 'rohit',
  database : 'mail_info'
});

connection.connect();
function checkupdate() {
connection.query('SELECT * from mail_info2', function(err, rows, fields) {
  if (err) throw err;

 
  value = rows;
  length = rows.length;
});
}

setInterval(checkupdate,30000);

var server = http.createServer(function(req, res) {

 
  var html = '<head><meta http-equiv="refresh" content="30"></head> <table> <tr><th>SENDER</th><th>SUBJECT</th><th>DATE</th></tr>';
  for (var i=0;i<length;i++)
{ 
html = html + "<tr><td>" + value[i].sender + "</td><td>" + value[i].subject + "</td><td>"+ value[i].date +"</td></tr>";
 }
  html = html + "</table>" ;
  res.end(html);

});


server.listen(port, serverUrl);

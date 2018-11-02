var options = {
    host: 'https://jobsense.sg/api',
    port: 443,
    path: '/api/service/'+servicename,
    // authentication headers
    headers: {
        'Authorization': 'Basic ' + new Buffer(username + ':' + passw).toString('base64')
    }
};
//this is the call
request = https.get(options, function(res){
    var body = "";
    res.on('data', function(data) {
        body += data;
    });
    res.on('end', function() {
        //here we have the full response, html or json object
        console.log(body);
    })
    res.on('error', function(e) {
        onsole.log("Got error: " + e.message);
    });
});
}
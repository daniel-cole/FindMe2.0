function() {
  karate.log('loading karate config')
  
  var env = karate.env; // get java system property 'karate.env'
 
  karate.log('karate env is: ' + env)
  
  if (!env) {
    env = 'dev'; // a custom 'intelligent' default
  }
  
  var port = karate.properties['findme.server.port'];
  
  //base config
  var config = { 
    env: env,
    appBaseUrl: 'http://127.0.0.1:' + port
  };
  
  karate.configure('connectTimeout', 5000);
  karate.configure('readTimeout', 5000);
  
  return config;
  
}
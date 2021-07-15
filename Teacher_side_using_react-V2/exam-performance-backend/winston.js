const winston = require('winston');
  require('winston-daily-rotate-file');
 
const transport = new (winston.transports.DailyRotateFile)({
    filename: './logs/application-%DATE%.log',
    datePattern: 'YYYY-MM-DD',
    maxFiles: '14d',
    prepend: true
});
 
const logger = winston.createLogger({
    level: 'debug',
    transports: [
      transport
    ]
});
 
module.exports = logger;
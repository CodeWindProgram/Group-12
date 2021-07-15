const env = process.env.NODE_ENV || 'development';

//CONFIGURATION FOR DEV ENVIRONMENT
const development = {
    DATABASE_CONFIG: {
        connectionLimit: 1000,
        host: 'localhost',
        port: 3306,
        user: 'root',
        password: 'root',
        database: 'exam_performance'
    },
    PORT : 8000
};

const production = {
    DATABASE_CONFIG: {
        connectionLimit: 1000,
        host: 'localhost',
        port: 3306,
        user: 'root',
        password: 'root',
        database: 'examperformance'
    },
    PORT : 8000
};

const test = {
    DATABASE_CONFIG: {
        connectionLimit: 1000,
        host: 'localhost',
        port: 3306,
        user: 'root',
        password: 'root',
        database: 'examperformance'
    },
    PORT : 8000
};

const config = {
    development,
    production,
    test
};

module.exports = config[env];
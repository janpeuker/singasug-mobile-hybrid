/** @jsx React.DOM */

var Stock = React.createClass({
    render : function(){
        var color = "#e12727";

        if (this.props.change >= 0){
            color = "#279c1a";
        }

        var divStyle = {
            "background-color": color
        };
        var price_string = "--";
        if (this.props.price){
            price_string = this.props.price.toFixed(2);
        }
        return (
            <div style={divStyle}>
                <div>{this.props.symbol} </div>
                <div>Price: {price_string} </div>
            </div>
        );
    }
});

function getRandomMessage(){
    var symbol = initial_data.content[Math.floor(Math.random()*initial_data.content.length)].symbol;
    return { "symbol": symbol, "price": Math.random() * 20, "asOfTime": 1234567890, change: -2 + Math.random() * 4};
}

var WebServer = {
    request:function(url,params, success){
        $.ajax({
            type: "GET",
            url: "" + url,
            contentType: 'application/json',
            dataType: 'json',
            success: function (data) {
                success(data);
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(url, status, err.toString());
            }.bind(this)
        });
    }
};

var StockApp = React.createClass({
    getInitialState: function() {
        return {data: []};
    },

    requestSuccess: function(data){
        this.setState({
            data: data
        });
    },

    requestStockSuccess: function(message){
        //Find symbol
        var current_data = this.state.data;
        current_data.forEach(function(entry) {
            if (entry.symbol === message.symbol){
                entry.price = message.price;
                //entry.change = message.change;
            }
        });

        this.setState({data: current_data});
    },

    tick: function() {
        var random_symbol = this.state.data[Math.floor(Math.random() * this.state.data.length)].symbol;
        WebServer.request("price/" + random_symbol, null, this.requestStockSuccess)
    },
    componentDidMount: function() {
        this.interval = setInterval(this.tick, 150);
        WebServer.request("stocks", null, this.requestSuccess)

    },
    componentWillUnmount: function() {
        clearInterval(this.interval);
    },

    render: function () {
        var stocks = this.state.data.map(function(stock){
            return (
                <Stock
                    key={stock.symbol}
                    symbol={stock.symbol}
                    exchange={stock.exchange}
                    price={stock.price}
                    change={stock.change}
                />
            );
        });
        return <div>Stocks {stocks}</div>;
    }
});

function getBotResponse(input) {
    //rock paper scissors
    if (input == "what do you sell") {
        return "All kind of shoes, You can check the catalog for shoes you're interested in!";
    } else if (input == "i have an issue with my order" || input == "order issue" || input == "order problem") {
        return "I'm sorry to hear about that. Please send us an email withh all the details in the contact page, Send us the order number and you're full name in the email. Thank you";
    } else if (input == "payment" || input == "how can i pay?" || input == "what payment do you accept") {
        return "We accept credit cards only for now.";
    } else if (input == "shipment" || input == "shipping" || input == "how long does it take for shipment" || input == "order") {
        return "It takes 2-3 business day for you order to arrive. You can track your orders in your profile.";
    }

    // Simple responses
    if (input == "hello" || input=="hi" || input=="hey" || input=="heyo") {
        return "Hello there!";
    } else if (input == "goodbye" || input == "bye" || input == "bai") {
        return "Talk to you later!";
    }else if (input == "thank you" || input=="thanks" || input=="thank you!" || input=="Thanks" || input=="Thank you") {
        return "No problem! I'm here if you need anything else."; }

    else {
        return "Try asking something else!";
    }
}

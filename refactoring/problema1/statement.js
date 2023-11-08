const plays = require("./data/plays.json");
const invoices = require("./data/invoices.json");

function statement(invoice, plays) {
  function amountFor(perf, play) {
    let thisAmount = 0;
    switch (play.type) {
      case "tragedy":
        thisAmount = 40000;
        if (perf.audience > 30) {
          thisAmount += 1000 * (perf.audience - 30);
        }
        break;
      case "comedy":
        thisAmount = 30000;
        if (perf.audience > 20) {
          thisAmount += 10000 + 500 * (perf.audience - 20);
        }
        thisAmount += 300 * perf.audience;
        break;
      default:
        throw new Error(`unknown type: ${play.type}`);
    }
    return thisAmount;
  }
  function calculateVolumeCredits(performances, plays) {
    let volumeCredits = 0;
    for (let perf of performances) {
      const play = plays[perf.playID];
      volumeCredits += Math.max(perf.audience - 30, 0);
      if ("comedy" === play.type) volumeCredits += Math.floor(perf.audience / 5);
    }
    return volumeCredits;
  }

  let totalAmount = 0;
  let volumeCredits = calculateVolumeCredits(invoice.performances, plays);

  function format(number) {
    return new Intl.NumberFormat("en-US", {
      style: "currency",
      currency: "USD",
      minimumFractionDigits: 2,
    }).format(number / 100);
  }

  function generateStatement(customer, performances, formattedAmount, formattedCredits) {
    let result = `Statement for ${customer}\n`;
    for (let perf of performances) {
      const play = plays[perf.playID];
      let thisAmount = amountFor(perf, play);
      result += `  ${play.name}: ${format(thisAmount)} (${perf.audience} seats)\n`;
      totalAmount += thisAmount;
    }
    result += `Amount owed is ${formattedAmount}\n`;
    result += `You earned ${formattedCredits} credits\n`;
    return result;
  }

  return generateStatement(invoice.customer, invoice.performances, format(totalAmount), volumeCredits);
}

console.log(statement(invoices[0], plays));
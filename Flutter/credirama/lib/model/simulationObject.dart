
import 'package:credirama/model/simulationPerDay.dart';
import 'package:credirama/model/transactionObject.dart';

class SimulationObject {

  Map<String, SimulationPerDay> dailyResult;
  double totalSum;
  double totalAvg;
  int totalNbTransaction;
  TransactionObject globalMinTransaction;
  TransactionObject globalMaxTransaction;

  SimulationObject({Map<String, SimulationPerDay> dailyResult, double totalSum, double totalAvg, int totalNbTransaction, TransactionObject globalMinTransaction, TransactionObject globalMaxTransaction}) {
    this.dailyResult = dailyResult;
    this.totalSum = totalSum;
    this.totalAvg = totalAvg;
    this.totalNbTransaction = totalNbTransaction;
    this.globalMinTransaction = globalMinTransaction;
    this.globalMaxTransaction = globalMaxTransaction;
  }

  @override
  String toString() {
    return 'SimulationObject{dailyResult: $dailyResult, totalSum: $totalSum, totalAvg: $totalAvg, totalNbTransaction: $totalNbTransaction, globalMinTransaction: $globalMinTransaction, globalMaxTransaction: $globalMaxTransaction}';
  }


}

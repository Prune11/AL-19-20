
import 'package:credirama/model/simulationPerDay.dart';

class Simulation {

  Map<String, SimulationPerDay> dailyResult;
  double totalSum;
  double totalAvg;
  int totalNbTransaction;

  Simulation({Map<String, SimulationPerDay> dailyResult, double totalSum, double totalAvg, int totalNbTransaction}) {
    this.dailyResult = dailyResult;
    this.totalSum = totalSum;
    this.totalAvg = totalAvg;
    this.totalNbTransaction = totalNbTransaction;
  }

  @override
  String toString() {
    return 'Simulation{dailyResult: $dailyResult, totalSum: $totalSum, totalAvg: $totalAvg, totalNbTransaction: $totalNbTransaction}';
  }


}

class SimulationPerDay {

  double sum;
  double avg;
  int nbTransaction;

  SimulationPerDay({double sum, double avg, int nbTransaction}) {
    this.sum = sum;
    this.avg = avg;
    this.nbTransaction = nbTransaction;
  }

  factory SimulationPerDay.fromJson(Map<String, dynamic> json) {
    return SimulationPerDay(
        sum: json['sum'],
        avg: json['avg'],
        nbTransaction: json['nbTransaction']);
  }

  @override
  String toString() {
    return 'SimulationPerDay{sum: $sum, avg: $avg, nbTransaction: $nbTransaction}';
  }

}

import 'package:charts_flutter/flutter.dart' as charts;
import 'package:flutter/material.dart';
import 'package:credirama/model/simulationPerDay.dart';

class VerticalBarLabelChart extends StatelessWidget {
  static const String FEES = "Fees";
  static const String AVG = "Avg";
  static const String NB_TRANSACTIONS = "NbTransactions";

  final List<charts.Series> seriesList;
  final bool animate;

  VerticalBarLabelChart(this.seriesList, {this.animate});

  factory VerticalBarLabelChart.feesPerDay(Map<String, SimulationPerDay> data) {
    return new VerticalBarLabelChart(
      _createFeesPerDay(data),
      animate: true,
    );
  }

  factory VerticalBarLabelChart.avgPerDay(Map<String, SimulationPerDay> data) {
    return new VerticalBarLabelChart(
      _createAvgPerDay(data),
      animate: true,
    );
  }

  factory VerticalBarLabelChart.nbTransactionsPerDay(Map<String, SimulationPerDay> data) {
    return new VerticalBarLabelChart(
      _createNbTransactionsPerDay(data),
      animate: true,
    );
  }


  // [BarLabelDecorator] will automatically position the label
  // inside the bar if the label will fit. If the label will not fit,
  // it will draw outside of the bar.
  // Labels can always display inside or outside using [LabelPosition].
  //
  // Text style for inside / outside can be controlled independently by setting
  // [insideLabelStyleSpec] and [outsideLabelStyleSpec].
  @override
  Widget build(BuildContext context) {
    return new charts.BarChart(
      seriesList,
      animate: animate,
      barRendererDecorator: new charts.BarLabelDecorator<String>(),
      domainAxis: new charts.OrdinalAxisSpec(),
      behaviors: [
        new charts.ChartTitle(getTitle(seriesList),
            behaviorPosition: charts.BehaviorPosition.top,
            titleOutsideJustification: charts.OutsideJustification.start,
            innerPadding: 20
        ),
      ],
    );
  }

  /// Create one series with sample hard coded data.
  static List<charts.Series<CumulativeData, String>> _createFeesPerDay(Map<String, SimulationPerDay> data) {
    final dataFees = [];
    data.forEach(
            (day, simulation) => dataFees.add(CumulativeData(day, simulation.sum))
    );

    return [
      new charts.Series<CumulativeData, String>(
          id: FEES,
          domainFn: (CumulativeData cD, _) => cD.date,
          measureFn: (CumulativeData cD, _) => cD.data,
          data: dataFees,
          // Set a label accessor to control the text of the bar label.
          labelAccessorFn: (CumulativeData cD, _) =>
          '${cD.data.toString()} €',
          //Change fill color
          colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
          fillColorFn: (_, __) =>
          charts.MaterialPalette.teal.shadeDefault,
      )
    ];
  }

  static List<charts.Series<CumulativeData, String>> _createAvgPerDay(Map<String, SimulationPerDay> data) {
    final dataAvg = [];
    data.forEach(
        (day, simulation) => dataAvg.add(CumulativeData(day, simulation.avg))
    );

    return [
      new charts.Series<CumulativeData, String>(
        id: AVG,
        domainFn: (CumulativeData cD, _) => cD.date,
        measureFn: (CumulativeData cD, _) => cD.data,
        data: dataAvg,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeData cD, _) =>
        '${cD.data.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault,
      )
    ];
  }

  static List<charts.Series<CumulativeData, String>> _createNbTransactionsPerDay(Map<String, SimulationPerDay> data) {
    final dataNbTransactions = [];
    data.forEach(
            (day, simulation) => dataNbTransactions.add(CumulativeData(day, simulation.nbTransaction.toDouble()))
    );

    return [
      new charts.Series<CumulativeData, String>(
        id: NB_TRANSACTIONS,
        domainFn: (CumulativeData cNT, _) => cNT.date,
        measureFn: (CumulativeData cNT, _) => cNT.data.toInt(),
        data: dataNbTransactions,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeData cNT, _) =>
        '${cNT.data.toInt().toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault,
      )
    ];
  }

  static String getTitle(List<charts.Series<CumulativeData, String>> chartList){
    String title = "";
    String chartID = chartList[0].id;
    switch(chartID) {
      case FEES:
        return "Fees per day";
      case AVG:
        return "Average per day";
      case NB_TRANSACTIONS:
        return "Number of transactions per day";
    }
    return title;
  }
}

/// Data structure to manage Fees and Avg per day
class CumulativeData {
  final String date;
  final double data;

  CumulativeData(this.date, this.data);
}
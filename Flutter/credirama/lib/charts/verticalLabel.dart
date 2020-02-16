import 'package:charts_flutter/flutter.dart' as charts;
import 'package:flutter/material.dart';

class VerticalBarLabelChart extends StatelessWidget {
  static const String FEES = "Fees";
  static const String AVG = "Avg";
  static const String NB_TRANSACTIONS = "NbTransactions";

  final List<charts.Series> seriesList;
  final bool animate;

  VerticalBarLabelChart(this.seriesList, {this.animate});

  factory VerticalBarLabelChart.feesPerDay(/*Map<DateTime, double> data*/) {
    return new VerticalBarLabelChart(
      _createFeesPerDay(/*data*/),
      animate: true,
    );
  }

  factory VerticalBarLabelChart.avgPerDay(/*Map<DateTime, double> data*/) {
    return new VerticalBarLabelChart(
      _createAvgPerDay(/*data*/),
      animate: true,
    );
  }

  factory VerticalBarLabelChart.nbTransactionsPerDay(/*Map<DateTime, int> data*/) {
    return new VerticalBarLabelChart(
      _createNbTransactionsPerDay(/*data*/),
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
  static List<charts.Series<CumulativeFees, String>> _createFeesPerDay(/*Map<DateTime, double> data*/) {
    final data = [
      new CumulativeFees('Janvier', 5.0),
      new CumulativeFees('Fevrier', 25.3),
      new CumulativeFees('Mars', 100.8),
      new CumulativeFees('Avril', 75.7),
    ];

    return [
      new charts.Series<CumulativeFees, String>(
          id: FEES,
          domainFn: (CumulativeFees cF, _) => cF.month,
          measureFn: (CumulativeFees cF, _) => cF.fees,
          data: data,
          // Set a label accessor to control the text of the bar label.
          labelAccessorFn: (CumulativeFees cF, _) =>
          '${cF.fees.toString()} €',
          //Change fill color
          colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
          fillColorFn: (_, __) =>
          charts.MaterialPalette.teal.shadeDefault,
      )
    ];
  }

  static List<charts.Series<CumulativeFees, String>> _createAvgPerDay(/*Map<DateTime, double> data*/) {
    final data = [
      new CumulativeFees('13-02-2020', 5.0),
      new CumulativeFees('14-02-2020', 25.3),
      new CumulativeFees('15-02-2020', 22.2),
      new CumulativeFees('16-02-2020', 37.5),
    ];

    return [
      new charts.Series<CumulativeFees, String>(
        id: AVG,
        domainFn: (CumulativeFees cF, _) => cF.month,
        measureFn: (CumulativeFees cF, _) => cF.fees,
        data: data,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeFees cF, _) =>
        '${cF.fees.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault,
      )
    ];
  }

  static List<charts.Series<CumulativeFees, String>> _createNbTransactionsPerDay(/*Map<DateTime, int> data*/) {
    final data = [
      new CumulativeFees('Janvier', 10),
      new CumulativeFees('Fevrier', 25),
      new CumulativeFees('Mars', 8),
      new CumulativeFees('Avril', 3),
    ];

    return [
      new charts.Series<CumulativeFees, String>(
        id: NB_TRANSACTIONS,
        domainFn: (CumulativeFees cF, _) => cF.month,
        measureFn: (CumulativeFees cF, _) => cF.fees,
        data: data,
        // Set a label accessor to control the text of the bar label.
        labelAccessorFn: (CumulativeFees cF, _) =>
        '${cF.fees.toString()} €',
        //Change fill color
        colorFn: (_, __) => charts.MaterialPalette.teal.shadeDefault,
        fillColorFn: (_, __) =>
        charts.MaterialPalette.teal.shadeDefault,
      )
    ];
  }

  static String getTitle(List<charts.Series<CumulativeFees, String>> chartList){
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

/// Sample ordinal data type.
class CumulativeFees {
  final String month;
  final double fees;

  CumulativeFees(this.month, this.fees);
}
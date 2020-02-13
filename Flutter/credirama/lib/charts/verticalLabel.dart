/// Vertical bar chart with bar label renderer example.
import 'package:charts_flutter/flutter.dart' as charts;
import 'package:flutter/material.dart';

class VerticalBarLabelChart extends StatelessWidget {
  final List<charts.Series> seriesList;
  final bool animate;

  VerticalBarLabelChart(this.seriesList, {this.animate});

  /// Creates a [BarChart] with sample data and no transition.
  factory VerticalBarLabelChart.withSampleData() {
    return new VerticalBarLabelChart(
      _createSampleData(),
      // Disable animations for image tests.
      animate: false,
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
      // Set a bar label decorator.
      // Example configuring different styles for inside/outside:
      //       barRendererDecorator: new charts.BarLabelDecorator(
      //          insideLabelStyleSpec: new charts.TextStyleSpec(...),
      //          outsideLabelStyleSpec: new charts.TextStyleSpec(...)),
      barRendererDecorator: new charts.BarLabelDecorator<String>(),
      domainAxis: new charts.OrdinalAxisSpec(),
    );
  }

  /// Create one series with sample hard coded data.
  static List<charts.Series<CumulativeFees, String>> _createSampleData() {
    final data = [
      new CumulativeFees('Janvier', 5.0),
      new CumulativeFees('Fevrier', 25.3),
      new CumulativeFees('Mars', 100.8),
      new CumulativeFees('Avril', 75.7),
    ];

    return [
      new charts.Series<CumulativeFees, String>(
          id: 'CumulativeFees',
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
}

/// Sample ordinal data type.
class CumulativeFees {
  final String month;
  final double fees;

  CumulativeFees(this.month, this.fees);
}
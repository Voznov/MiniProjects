#include <iostream>
#include <cstdlib>
#include <ctime>
#include "..\NN_class\neuron.h"

using namespace std;

int main()
{
	srand(time(NULL));

	Sigmoid aFunction;

	Neuron in0, in1;

	vector<Neuron*> inputNeurons, shadowNeurons;
	inputNeurons.push_back(&in0);
	inputNeurons.push_back(&in1);

	Neuron s0(&aFunction, inputNeurons);
	Neuron s1(&aFunction, inputNeurons);

	shadowNeurons.push_back(&s0);
	shadowNeurons.push_back(&s1);

	Neuron out0(&aFunction, shadowNeurons);

	for (int i = 0; i < 50000; ++i)
	{
		int a = rand() % 2;
		int b = rand() % 2;

		try
		{
			in0.calculateResult(a);
			in1.calculateResult(b);
			s0.calculateResult();
			s1.calculateResult();
			float resultNN = out0.calculateResult();

			float result = (!a) && (!b);
			float tSpeed = 1;
			
			cout << "Input: " << a << " " << b << endl;
			cout << "Error: " << abs(resultNN - result) << endl;

			out0.calculateDelta(tSpeed, result);
			s0.calculateDelta(tSpeed);
			s1.calculateDelta(tSpeed);

			out0.changeWeights();
			s0.changeWeights();
			s1.changeWeights();
		}
		catch(int e)
		{
			cout << "kek: " << e << endl;
			return 0;
		}
	}
		return 0;
}

#ifndef __MAIN_H__
#define __MAIN_H__

#include <iostream>

#define DLL_EXPORT __declspec(dllexport) _stdcall

#ifdef __cplusplus
extern "C"
{
#endif

int DLL_EXPORT ReturnKek(int a);

#ifdef __cplusplus
}
#endif

#endif // __MAIN_H__

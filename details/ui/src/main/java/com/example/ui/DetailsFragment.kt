package com.example.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.entities.Vacancy
import com.example.ui.databinding.FragmentDetailsBinding
import com.example.ui.rv_adapters.QuestionAdapter
import com.example.ui.rv_adapters.decorators.QuestionDecorator
import com.example.utils.getPluralAddition
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModel<DetailsViewModel>()
    private val questionAdapter by lazy {
        QuestionAdapter {
            findNavController().navigate(
                com.core.ui.R.id.action_detailsFragment_to_replyBottomSheet,
                bundleOf(
                    ARGS_REPLY to it, ARGS_REPLY to it,
                    ARGS_TITLE to binding.jobTitle.text.toString()
                )
            )
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vacancy = requireArguments().get("vacancy") as Vacancy
        setUpQuestionsAdapter()
        setUpDetails(vacancy)
        binding.toolbarVacancy.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setUpDetails(vacancy: Vacancy) {
        with(binding) {
            jobTitle.text = vacancy.title
            detailsSalary.text = vacancy.salary.full
            requiredExperience.text = "Требуемый опыт: " + vacancy.experience.text
            workload.text =
                vacancy.schedules.joinToString(separator = ", ").replaceFirstChar(Char::titlecase)
            if (vacancy.appliedNumber != null) {
                peopleApplied.text = buildString {
                    append(vacancy.appliedNumber.toString())
                    append(
                        " " + vacancy.appliedNumber?.getPluralAddition(
                            "человек",
                            "человека",
                            "человек"
                        )
                    )
                    append(" уже откликнулось")
                }
            } else {
                peopleAppliedContainer.visibility = GONE
            }
            if (vacancy.lookingNumber != null) {
                nowLooking.text = buildString {
                    append(vacancy.lookingNumber.toString())
                    append(
                        " " + vacancy.lookingNumber?.getPluralAddition(
                            "человек",
                            "человека",
                            "человек"
                        )
                    )
                    append(" cейчас смотрят")
                }
            } else {
                nowLookingContainer.visibility = GONE
            }
            if (peopleAppliedContainer.visibility == GONE && nowLookingContainer.visibility == GONE) {
                detailsPeopleInfoContainer.visibility = GONE
            }
            companyName.text = vacancy.company
            address.text =
                "${vacancy.address.town}, " + "${vacancy.address.street}, " + "${vacancy.address.house}"
            if (vacancy.description != null) {
                description.text = vacancy.description
            } else {
                description.visibility = GONE
            }
            responsibilitiesDescription.text = vacancy.responsibilities
            questionAdapter.submitList(vacancy.questions)
            replyBtn.setOnClickListener {
                findNavController().navigate(com.core.ui.R.id.action_detailsFragment_to_replyBottomSheet,
                    bundleOf(
                        ARGS_TITLE to binding.jobTitle.text.toString()
                    ))
            }
            if (vacancy.isFavorite) {
                ivFavorites.setImageResource(com.core.ui.R.drawable.ic_heart_filled)
            } else {
                ivFavorites.setImageResource(com.core.ui.R.drawable.ic_empty_heart)
            }
            setUpFavoritesIcon(vacancy)
        }
    }

    private fun setUpFavoritesIcon(vacancy: Vacancy) {
        binding.ivFavorites.setOnClickListener {
            if (vacancy.isFavorite) {
                viewModel.deleteFromFavorites(vacancy)
                binding.ivFavorites.setImageResource(com.core.ui.R.drawable.ic_empty_heart)
                vacancy.isFavorite = false
            } else {
                viewModel.addToFavorites(vacancy)
                binding.ivFavorites.setImageResource(com.core.ui.R.drawable.ic_heart_filled)
                vacancy.isFavorite = true
            }
        }
    }
    private fun setUpQuestionsAdapter() {
        binding.detailsQuestionsRecycler.apply {
            adapter = questionAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(QuestionDecorator(8))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    companion object {
        const val ARGS_REPLY = "reply"
        const val ARGS_TITLE = "job_title"
    }
}